import {Component, OnInit} from '@angular/core';
import {FoodService} from "../services/food.service";

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css']
})
export class ProductsListComponent implements OnInit {
  img = "/assets/img/";

  //categoryes = soup, garnish, meat, extra, drinks
  categories = ["SOUP", "PASTA", "MEAT", "FISH", "DESSERT", "DRINKS"];
  foodList: any = [];


  constructor(private foodService: FoodService) {
  }

  ngOnInit(): void {
    this.foodService.getFoodList().subscribe((data) => {
      this.foodList=data;
      console.log(this.foodList);
      },
      error => {
      console.log(error);
      })
  }



}

