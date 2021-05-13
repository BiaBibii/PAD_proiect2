import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../services/user.service";
import {User} from "../models/user";
import {ToastrService} from "ngx-toastr";
import {TokenStorageService} from "../services/token-storage.service";


@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  password: string | undefined;
  loggedIn = false;
  username: string | undefined;

  private user: User | undefined;
  private error: string | undefined;
  form: any = {};
  roles: string[] = [];

  constructor( private tokenStorage: TokenStorageService, private toastr: ToastrService, private userService: UserService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.loggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  onSubmit() {

    this.userService.login(this.form).subscribe(data => { // success path
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);
        this.roles = this.tokenStorage.getUser().roles;
        // to do
        this.loggedIn = true;
        this.router.navigate(['acasa']);
        this.userService.logInSuccess(this.form.username);
      },
      error => { // error path
        this.toastr.error("Incorrect username or password");
      });
  }


}
