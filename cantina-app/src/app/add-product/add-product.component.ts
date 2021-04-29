import {Component, OnInit} from '@angular/core';
import {Product} from "../models/product";
import {FoodService} from "../services/food.service";

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  product: Product|undefined;
  title: any;

  constructor(private foodSerivce: FoodService) {

  }

  ngOnInit(): void {
  }

  add(value: any) {

  }

  addProduct() {
  }

  // getProduct(){
  //   console.log("ceva");
  //   this.foodSerivce.getFoodList().subscribe(data => {
  //       console.log(data);
  //     },
  //     error => { // error path
  //       console.log(error);
  //     });
  // }
}
