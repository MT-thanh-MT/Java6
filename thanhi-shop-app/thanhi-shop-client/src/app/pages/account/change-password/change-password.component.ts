import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../core/services/user.service";
import {ToastService} from "../../../shared/toast/toast-service";
import Validation from "../../../shared/validations/Validation";
import {UserAuthService} from "../../../core/services/user-auth.service";
import {UserDetail} from "../../../shared/model/userDetail";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  form: FormGroup = new FormGroup({
    password: new FormControl(''),
    confirmPassword: new FormControl(''),
    acceptTerms: new FormControl(false),
  });
  loading = false;
  submitted = false;
  isMatchCurrentPass: boolean = false;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private userService: UserService,
              private userAuthService: UserAuthService,
              private toast: ToastService) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group(
      {
        currentPassword: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(40)
          ]
        ],
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

  onReset() {
    this.submitted = false;
    this.form.reset();
  }

  onSubmit() {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    } else {
      let {confirmPassword, password, currentPassword} = this.form.value;
      let user: UserDetail = this.userAuthService.getUserDetail();
      if (user.password != currentPassword) {
        this.isMatchCurrentPass = true;
        this.toast.show('Current Password does not match! ', { classname: 'bg-danger text-light', delay: 5000 });
        return;
      }
    }
  }

}
