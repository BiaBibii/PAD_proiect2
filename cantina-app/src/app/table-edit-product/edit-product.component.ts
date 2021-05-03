import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FoodService} from "../services/food.service";

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {


  foodList: any = [];


  constructor(private foodService: FoodService ,private router:Router) { }

  ngOnInit(): void {

    this.foodService.getFoodList().subscribe((data) => {
        this.foodList=data;
        console.log(this.foodList);
      },
      error => {
        console.log(error);
      })
  }

  addNewProduct() {
    this.router.navigate(['admin/add-product']);
  }

  delete() {

  }
}
