import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators, ɵFormGroupValue} from "@angular/forms";
import {UserService} from "../../../core/services/user.service";
import {first} from "rxjs/operators";
import Validation from "../../../shared/validations/Validation";
import {UserDetail} from "../../../shared/model/userDetail";
import {ToastService} from "../../../shared/toast/toast-service";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  form: FormGroup = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    username: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl(''),
    acceptTerms: new FormControl(false),
  });
  loading = false;
  submitted = false;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private userService: UserService,
              private toast: ToastService) {
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group(
      {
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        username: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(20)
          ]
        ],
        email: ['', [Validators.required, Validators.email]],
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(40)
          ]
        ],
        confirmPassword: ['', Validators.required],
        acceptTerms: [false, Validators.requiredTrue]
      },
      {
        validators: [Validation.match('password', 'confirmPassword')]
      }
    );
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    } else {
      this.userService.register(this.convertToUserDetail(this.form)).subscribe(
        (response: any) => {
          this.router.navigate(['/login']);
          this.toast.show('Register Success. Now you can sign with new account', { classname: 'bg-success text-light', delay: 5000 });
        },
        (err) => {
          console.log(err);
          this.toast.show('Register Fail! ' + err.error.message, { classname: 'bg-danger text-light', delay: 5000 });
        }
      );
    }

    // console.log(JSON.stringify(this.form.value, null, 2));
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }

  private convertToUserDetail(formData: FormGroup) {
    let {username, password, firstName, lastName, email} = formData.value;
    let userDetail: UserDetail = new UserDetail(username, password, firstName, lastName, email);
    // console.log(userDetail);
    userDetail.roles = ['CUSTOMER'];
    return userDetail;
  }
}
