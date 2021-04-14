import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../services/user.service";
import {User} from "../models/user";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  password: string | undefined;
  loggedIn=false;
  username: string | undefined;

  private user: User | undefined;
  private error: string | undefined;

  constructor(private toastr: ToastrService, private userService: UserService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
  }

  onSubmit(form: any) {
    // @ts-ignore
    this.user = new User(form.username, form.password);

    this.userService.logIn(this.user).subscribe(() => { // success path
        // to do
        this.loggedIn = true;
        this.router.navigate(['acasa']);
      },
      error => { // error path
        this.toastr.error(error.error.message);
      });
  }


}
