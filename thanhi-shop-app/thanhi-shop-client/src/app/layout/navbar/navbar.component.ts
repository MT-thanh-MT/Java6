import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {UserAuthService} from "../../core/services/user-auth.service";
import {UserService} from "../../core/services/user.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  collapsed = true;
  currentChoice: string = 'home';

  public setActive(choice: string): void{
    this.currentChoice = choice;
  }

  public getActive(choice: string) : boolean{
    if(this.currentChoice == choice)
      return true;
    else
      return false;

  }

  constructor(public userAuthService: UserAuthService,
              private router: Router,
              public userService: UserService) { }

  ngOnInit(): void {
  }

  public isLoggedIn() {
    return this.userAuthService.isLoggedIn();
  }

  public logout(): void {
    this.userAuthService.clearAuth();
    this.router.navigate(['/login']);
  }

}
