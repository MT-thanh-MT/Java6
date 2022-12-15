import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  PATH_OF_API = "http://localhost:8080";

  requestHeader = new HttpHeaders(
    { "No-Auth": "True" }
  );

  constructor(private httpClient: HttpClient, private userAuthService: UserAuthService) { }

  public login(loginData: NgForm) {
    return this.httpClient.post(this.PATH_OF_API + "/authenticate", loginData.value, { headers: this.requestHeader });
  }

  public roleMath(allowedRoles: string[]): boolean {
    let isMath = false;
    const userRoles: any = this.userAuthService.getRoles();

    if (userRoles != null && userRoles) {
      for (let i = 0; i < userRoles.length; i++) {
        for (let j = 0; j < allowedRoles.length; j++) {

          if (userRoles[i] === allowedRoles[i]) {
            isMath = true;
            return isMath;
          }
        }
      }
    }
    return isMath;
  }
}
