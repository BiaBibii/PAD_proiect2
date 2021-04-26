import {Injectable} from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router,
  Route,
  UrlSegment
} from '@angular/router';
import {Observable} from 'rxjs';
import {UserService} from "./services/user.service";

@Injectable({
  providedIn: 'root'
})

export class LoginAuthGuard implements CanActivate {

  constructor(private authService: UserService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let url: string = state.url;
    return this.checkUserLogin(next, url);
  }

  canActivateChild(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.canActivate(next, state);
  }

  canDeactivate(
    component: unknown,
    currentRoute: ActivatedRouteSnapshot,
    currentState: RouterStateSnapshot,
    nextState?: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return true;
  }

  canLoad(
    route: Route,
    segments: UrlSegment[]): Observable<boolean> | Promise<boolean> | boolean {
    return true;
  }

  checkUserLogin(route: ActivatedRouteSnapshot, url: any): boolean {
    if (this.authService.isLoggedIn()) {
      const userRole = this.authService.getRole().getValue();
      if (route.data.role && route.data.role.indexOf(userRole) === -1) {
        this.router.navigate(['/acasa']);
        return false;
      }
      return true;
    }

    this.router.navigate(['/admin']);
    return false;
  }

}
