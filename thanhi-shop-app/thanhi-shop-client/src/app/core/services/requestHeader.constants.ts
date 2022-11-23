import {HttpHeaders} from "@angular/common/http";
import {UserAuthService} from "./user-auth.service";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class RequestHeaderConstants  {

  constructor(private userAuthService: UserAuthService) {
  }

  public NO_AUTH: HttpHeaders = new HttpHeaders(
    { "No-Auth": "True" }
  );

  public Authorization(): HttpHeaders {
    return new HttpHeaders(
      { "Authorization": "Bearer " + this.userAuthService.getToken()}
    );
  }
}
