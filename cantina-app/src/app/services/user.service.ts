import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})

export class UserService {
  private url = 'http://localhost:8080/api/';

  isLogin = new BehaviorSubject<boolean>(false);
  roleAs = new BehaviorSubject("");

  constructor(private http: HttpClient) {
    // get user session
    const loggedIn = localStorage.getItem('STATE');
    if (loggedIn == 'true')
      this.isLogin.next(true);
    else
      this.isLogin.next(false);

    //get user role
    this.roleAs.next(localStorage.getItem('ROLE') ?? "");
  }


  register(user: User) {
    return this.http.post<User>(this.url + 'register', user);
  }


  logIn(user: User) {
    return this.http.post<User>(this.url + 'login', user);
  }


  logInSuccess(username: string) {
    this.isLogin.next(true);
    if (username == "admin")
      this.roleAs.next("ROLE_ADMIN");
    else
      this.roleAs.next("ROLE_USER");
    localStorage.setItem('STATE', 'true');
    localStorage.setItem('ROLE', this.roleAs.getValue());
  }

  logOut() {
    this.isLogin.next(false);
    this.roleAs.next('');
    localStorage.setItem('STATE', 'false');
    localStorage.setItem('ROLE', '');
    return this.http.get(this.url + 'logout');
  }

  isLoggedIn() {
    return this.isLogin.getValue();
  }

  getRole() {
    return this.roleAs;
  }

  forgetPassword(email: string) {
    return this.http.post(this.url + 'forgetPassword', email)
  }


}
