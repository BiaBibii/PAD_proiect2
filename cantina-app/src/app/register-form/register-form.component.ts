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

  private user: User | undefined;

  constructor(private router: Router, private toastr: ToastrService, private userService: UserService) {
  }

  ngOnInit(): void {
  }

  register(form: any) {
    this.user = new User(form.username, form.password, 1, form.email, form.prenume, form.nume);
    console.log(this.user);
    this.userService.register(this.user).subscribe((data: User) => {
        this.user = {
          id: data.id,
          first_name: data.first_name,
          last_name: data.last_name,
          username: data.username,
          email: data.email,
          password: data.password,};
        this.registered=true;
        this.router.navigate(['login']);
      },
    error => { // error path
      this.toastr.error(error.error.message);
    }
  );

  }
}
