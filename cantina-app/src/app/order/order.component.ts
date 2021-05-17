import { Component, OnInit } from '@angular/core';
import {FoodService} from "../services/food.service";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  orderList: any = [];


  constructor(private foodService: FoodService) {
  }

  ngOnInit(): void {
    this.foodService.seeOrders().subscribe((data)=>{
      this.orderList=data;
      console.log(data);
    },error => {
      console.log(error);
    });
  }



}
