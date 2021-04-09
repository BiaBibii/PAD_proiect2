import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {
  email: string | undefined;
  password:string |undefined;
  loggedIn:boolean |undefined;
  nume: string | undefined;
  prenume:string | undefined;
  telefon:string |undefined;
  constructor() { }

  ngOnInit(): void {
  }
  register(form:any){
    //salvam in baza de date
    console.log("M-am inregistrat: ", form.email, form.nume, form.prenume,form.telefon);


  }
}
