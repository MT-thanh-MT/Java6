import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {FormGroup, NgForm} from '@angular/forms';
import { UserAuthService } from './user-auth.service';
import {RequestHeaderConstants} from "./requestHeader.constants";
import {Observable} from "rxjs";
import {UserDetail} from "../../shared/model/userDetail";
import {SearchRequestDTO} from "../../shared/model/SearchRequestDTO";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  PATH_OF_API = "http://localhost:8080";

  constructor(private httpClient: HttpClient,
              public userAuthService: UserAuthService,
              private router: Router,
              private requestHeaderConstants: RequestHeaderConstants) { }

  public login(loginData: NgForm): Observable<UserDetail> {
    return this.httpClient.post<UserDetail>(this.PATH_OF_API + "/authenticate", loginData.value, { headers: this.requestHeaderConstants.NO_AUTH });
  }

  public logout(){
    this.userAuthService.clearAuth();
    this.router.navigate(['/login']);
  }

  public roleMath(allowedRoles: string[]): boolean {
    let isMath = false;
    const userRoles: any = this.userAuthService.getRoles();
    if (userRoles != null && userRoles) {
      for (let i = 0; i < userRoles.length; i++) {
        for (let j = 0; j < allowedRoles.length; j++) {
          if (userRoles[i] === allowedRoles[j]) {
            isMath = true;
          }
        }
      }
    }
    return isMath;
  }

  register(registerData: UserDetail) {
    return this.httpClient.post<UserDetail>(this.PATH_OF_API + "/api/account/sing-up", registerData, { headers: this.requestHeaderConstants.NO_AUTH });
  }

  public getAll(): Observable<UserDetail[]> {
    return this.httpClient.get<UserDetail[]>(this.PATH_OF_API + "/admin/account", { headers: this.requestHeaderConstants.Authorization() });
  }

  public search(value: string): Observable<UserDetail[]> {
    let searchRequestDTO: SearchRequestDTO = new SearchRequestDTO();
    searchRequestDTO.text = value;
    searchRequestDTO.fields = ["username", "firstName", "lastName", "email"];
    return this.httpClient.post<UserDetail[]>(this.PATH_OF_API + "/admin/account/search", searchRequestDTO, { headers: this.requestHeaderConstants.Authorization() });
  }

  updateAuthority(user: UserDetail): Observable<UserDetail> {
    return this.httpClient.put<UserDetail>(this.PATH_OF_API + "/admin/authority", user, { headers: this.requestHeaderConstants.Authorization() });
  }

  update(user: UserDetail): Observable<UserDetail> {
    console.log(user)
    return this.httpClient.put<UserDetail>(this.PATH_OF_API + "/admin/account", user, { headers: this.requestHeaderConstants.Authorization() });
  }

  create(user: UserDetail): Observable<UserDetail> {
    return this.httpClient.post<UserDetail>(this.PATH_OF_API + "/admin/account", user, { headers: this.requestHeaderConstants.Authorization() });
  }

  delete(id?: number) {
    return this.httpClient.delete(this.PATH_OF_API + "/admin/account?id=" + id, { headers: this.requestHeaderConstants.Authorization() });
  }

  save(user: UserDetail): Observable<UserDetail> {
    if (user.id) {
      user.modifiedBy = this.userAuthService.getUserDetail().username;
      user.modifiedDate = new Date();
      return this.update(user);
    } else {
      user.createBy = this.userAuthService.getUserDetail().username;
      user.createDate = new Date();
      return this.create(user);
    }
  }

  public getLoggedInUser(): UserDetail {
    return this.userAuthService.getUserDetail();
  }
}
