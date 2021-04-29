import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../models/user";
import {BehaviorSubject, Observable} from "rxjs";
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})




export class UserService {
  private url = 'http://localhost:8080/api/';

  isLogin = new BehaviorSubject<boolean>(false);
  roleAs = new BehaviorSubject("");
  private role: string[]| undefined;

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


  register(user: User): Observable<any>{
    if(user.username==="admin")
      this.role=["admin"];
    else
      this.role=["user"];
    return this.http.post(this.url + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password,
      role: this.role
    }, httpOptions);
  }


  login(credentials:any): Observable<any> {
    return this.http.post(this.url + 'signin', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
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
