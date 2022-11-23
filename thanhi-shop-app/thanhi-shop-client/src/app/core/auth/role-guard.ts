import {Injectable} from '@angular/core';
import {Router, ActivatedRouteSnapshot} from '@angular/router';
import {UserAuthService} from "../services/user-auth.service";
import {UserService} from "../services/user.service";
import {ToastService} from "../../shared/toast/toast-service";

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService {

  constructor(private getUser: UserAuthService,
              private userService: UserService,
              private router: Router,
              private toast: ToastService) {
  }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    if (!this.getUser.isLoggedIn()) {
      this.router.navigate(['/login']);
      this.toast.show('Please Sing in now', {classname: 'bg-danger text-light', delay: 5000});
      return false;
    } else {
      if (this.userService.roleMath(route.data['roles'])) {
        return true;
      } else {
        this.router.navigate(['access-denied']);
        this.toast.show('Access denied!', {classname: 'bg-danger text-light', delay: 5000});
        return false;
      }
    }
  }

}
