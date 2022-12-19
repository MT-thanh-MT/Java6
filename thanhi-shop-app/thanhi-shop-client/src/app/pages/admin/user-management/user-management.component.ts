import {Component, EventEmitter, OnInit, TemplateRef, ViewEncapsulation} from '@angular/core';
import {UserDetail} from "../../../shared/model/userDetail";
import {Role} from "../../../shared/model/Role";
import {UserService} from "../../../core/services/user.service";
import {RoleService} from "../../../core/services/role.service";
import {Router} from "@angular/router";
import {ToastService} from "../../../shared/toast/toast-service";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import Validation from "../../../shared/validations/Validation";
import {NgbModal, NgbModalConfig} from "@ng-bootstrap/ng-bootstrap";
import Swal from "sweetalert2";

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class UserManagementComponent implements OnInit {

  page = 1;
  pageSize = 5;
  users!: UserDetail[];
  roles!: Role[];
  loading = false;
  submitted = false;
  modalTitle: string = 'Modal';
  form!: FormGroup;
  listOfSelectedRole = ['CUSTOMER'];


  constructor(
    private userService: UserService,
    private roleService: RoleService,
    private router: Router,
    private toast: ToastService,
    config: NgbModalConfig,
    private modalService: NgbModal,
    private formBuilder: FormBuilder) {

    config.backdrop = 'static';
    config.keyboard = false;

  }

  ngOnInit(): void {
    this.load();
    this.form = this.createUserForm(new UserDetail());
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  private load() {
    this.userService.getAll().subscribe(
      (res) => {
        this.users = res;
        let index = this.users.findIndex(user => user.id == this.userService.userAuthService.getUserDetail().id);
        this.users.splice(index,1);
      },
      (error) => {
        if (error.status == 401) {
          this.router.navigate(['/access-denied']);
        }
        console.log(error);
      }
    );

    this.roleService.getAll().subscribe(
      (res) => {
        this.roles = res;
      },
      (error) => {
        if (error.status == 401) {
          this.router.navigate(['/access-denied']);
        }
        console.log(error);
      }
    );
  }

  searchByUser(value: string) {
    this.userService.search(value).subscribe(
      (res) => {
        this.users = res;
      },
      (error) => {
        console.log(error);
        if (error.status == 401) {
          this.router.navigate(['/access-denied']);
        }
      }
    );
  }

  openModal(content: TemplateRef<any>, user: UserDetail | null) {
    if (user) {
      this.modalTitle = 'Update user';
      this.form = this.createUserForm(user);
      this.listOfSelectedRole = user.roles;

    } else if (user == null) {
      this.modalTitle = 'Create new user';
      this.form = this.createUserForm(new UserDetail());
    }
    this.modalService.open(content, {size: 'xl'});
  }

  private createUserForm(userDetail: UserDetail) {
    return this.formBuilder.group(
      {
        id: [userDetail.id],
        firstName: [userDetail.firstName, [Validators.required]],
        lastName: [userDetail.lastName, [Validators.required]],
        username: [
          userDetail.username,
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(20)
          ]
        ],
        email: [userDetail.email, [Validators.required, Validators.email]],
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(40)
          ]
        ],
        confirmPassword: ['', [Validators.required]],
        activated: [userDetail.activated, [Validators.requiredTrue]]
      },
      {
        validators: [Validation.match('password', 'confirmPassword')]
      }
    );
  }

  delete(user: UserDetail) {
    Swal.fire({
      title: 'Do you want to delete account: ' + user.username,
      showCancelButton: true,
      confirmButtonText: 'Delete'
    }).then((result) => {
      if (result.isConfirmed) {
        this.userService.delete(user.id).subscribe(
          (res) => {
            let index = this.users.findIndex(u => u.id == user.id);
            this.users.splice(index, 1);
            this.toast.show('Delete account Success.', {classname: 'bg-success text-light', delay: 5000});
          },
          (error) => {
            console.log(error)
            this.toast.show(error.message, {classname: 'bg-danger text-light', delay: 5000});
          }
        )
      }
    })
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.form.invalid) {
      console.log(this.form.status);
      return;
    } else {
      const user: UserDetail = {
        ...this.form.value
      }
      user.roles = this.listOfSelectedRole;
      console.log(user)
      let message = user.id == null ? 'Create new account Success.': 'Update account Success.';
      let errorMessage = user.id ? 'Create new account fail. ': 'Update account fail. ';
      this.userService.save(user).subscribe(
        (res) => {
          if (user.id) {
            let index = this.users.findIndex(u => u.id == user.id);
            this.users[index] = res;
          } else {
            this.users.push(res);
          }
          this.toast.show(message, {classname: 'bg-success text-light', delay: 5000});
        },
        (error) => {
          if (error.status == 401) {
            this.router.navigate(['/logout']);
            this.toast.show('Your session is timed out. please sing in again', {
              classname: 'bg-danger text-light',
              delay: 5000
            });
          }
          this.toast.show(errorMessage + error.error.message, {classname: 'bg-danger text-light', delay: 5000});
        }
      );
    }
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
    this.listOfSelectedRole = [];
  }

  authorityOf(user: UserDetail, role: Role) {
    return user.roles.find(ur => ur == role.name);
  }

  authorityChanged(user: UserDetail, role: Role) {
    let authority = this.authorityOf(user, role);
    if (authority) {
      let index = user.roles.findIndex(ar => ar == role.name);
      user.roles.splice(index, 1);
      this.updateAuthority(user);
    } else {
      // @ts-ignore
      user.roles.push(role.name);
      this.updateAuthority(user);
    }
  }

  private updateAuthority(user: UserDetail) {
    this.userService.updateAuthority(user).subscribe(
      (res) => {
        this.users.forEach(user => {
          if (user.id == res.id) {
            user = res;
            this.toast.show('Update authority success', {classname: 'bg-success text-light', delay: 3000});
          }
        });
      },
      (error) => {
        console.log(error);
        if (error.status == 401) {
          this.router.navigate(['/access-denied']);
        }
        this.toast.show('Update authority fail. ' + error.message, {classname: 'bg-danger text-light', delay: 3000});
      }
    );
  }

  activatedChanged(user: UserDetail, $event: boolean) {
    console.log(user)
    console.log(user.activated)
    user.activated = $event;

    this.userService.updateAuthority(user).subscribe(
      (res) => {
        // console.log(res)
        this.toast.show('Activate changed success', {classname: 'bg-success text-light', delay: 3000});
      },
      (error) => {
        console.log(error);
        if (error.status == 401) {
          this.router.navigate(['/access-denied']);
        }
        this.toast.show('Update activate fail. ' + error.message, {classname: 'bg-danger text-light', delay: 3000});

      }
    );
  }
}
