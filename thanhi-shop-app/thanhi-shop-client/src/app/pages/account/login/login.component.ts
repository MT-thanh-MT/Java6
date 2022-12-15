import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import {UserService} from "../../../core/services/user.service";
import {UserAuthService} from "../../../core/services/user-auth.service";
import {ToastService} from "../../../shared/toast/toast-service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService,
              private userAuthService: UserAuthService,
              private router: Router,
              private toast: ToastService) { }

  ngOnInit(): void {
  }

  login(loginForm: NgForm): void {
    if (loginForm.valid) {
      this.userService.login(loginForm).subscribe(
        (response: any) => {
          this.userAuthService.setRoles(response.account.roles);
          this.userAuthService.setUserDetail(response.account);
          this.userAuthService.setToken(response.jwtToken);
          this.router.navigate(['/home']);
          this.toast.show('Login Success', { classname: 'bg-success text-light', delay: 5000 });
        },
        (err) => {
          console.log(err);
          this.toast.show('Login Fail! ' + err.error.message, { classname: 'bg-danger text-light', delay: 5000 });
        }
      );
    }


  }

}
