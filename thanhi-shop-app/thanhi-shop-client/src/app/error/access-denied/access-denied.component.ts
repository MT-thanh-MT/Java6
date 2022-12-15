import { Component, OnInit } from '@angular/core';
import {UserAuthService} from "../../core/services/user-auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-access-denied',
  templateUrl: './access-denied.component.html',
  styleUrls: ['./access-denied.component.css']
})
export class AccessDeniedComponent implements OnInit {

  constructor(private userAuthService: UserAuthService,
              private router: Router,) { }

  ngOnInit(): void {
  }

  logout() {
    this.userAuthService.clearAuth();
    this.router.navigate(['/login']);
  }
}
