import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent implements OnInit {
  email: string| undefined;
  sentMail: boolean| undefined;

  constructor(private router: Router, private toastr: ToastrService, private userService: UserService) { }

  ngOnInit(): void {
  }

  onSubmit(form: any) {
    console.log(form.email);
    this.userService.forgetPassword(form.email).subscribe(() => { // success path
        // to do
        this.sentMail = true;
        this.router.navigate(['login']);
      },
      error => { // error path
        console.log(error);
        this.toastr.error(error.error.message);
      });
  }
}
