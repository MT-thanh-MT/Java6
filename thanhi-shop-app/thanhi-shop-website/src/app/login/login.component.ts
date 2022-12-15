import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router) { }

  ngOnInit(): void {
  }

  login(loginForm: NgForm): void {
    this.userService.login(loginForm).subscribe(
      (response: any) => {
        this.userAuthService.setRoles(response.account.roles);
        this.userAuthService.setToken(response.jwtToken);
        this.router.navigate(['/home']);
      },
      (err) => {
        console.log(err);

      }
    );

  }
}
