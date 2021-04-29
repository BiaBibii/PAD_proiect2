import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FoodService} from "../services/food.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {


  constructor(private router: Router) { }

  ngOnInit(): void {

  }


}
