import {Component, OnInit} from '@angular/core';
import {UserService} from "../services/user.service";
import {User} from "../models/user";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";


@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})

export class RegisterFormComponent implements OnInit {
  nume: string | undefined;
  prenume: string | undefined;
  username: string | undefined;
  email: string | undefined;
  password: string | undefined;
  registered: boolean| undefined;

  form: any = {};

  private user: User | undefined;

  constructor(private router: Router, private toastr: ToastrService, private userService: UserService) {
  }

  ngOnInit(): void {
  }

  register() {
    // this.user = new User(form.username, form.password, 1, form.email, form.prenume, form.nume);
    console.log(this.form);

    this.userService.register(this.form).subscribe((data) => {
        console.log(data);
        this.registered=true;
        this.router.navigate(['login']);
      },
    error => { // error path
      console.log(error);
      this.toastr.error(error.error.message);
    }
  );

  }
}
