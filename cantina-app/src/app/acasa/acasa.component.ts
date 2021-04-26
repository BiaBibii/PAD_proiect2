import { Component, OnInit } from '@angular/core';
import {User} from "../models/user";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-acasa',
  templateUrl: './acasa.component.html',
  styleUrls: ['./acasa.component.css']
})
export class AcasaComponent implements OnInit {
  img_top="/assets/img/contact_top.jpg";
  img_middle="/assets/img/home.gif";
  constructor() { }

  ngOnInit(): void {

  }

}
