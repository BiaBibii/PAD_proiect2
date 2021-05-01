import {Component, Input, OnInit, Output} from '@angular/core';
import {Product} from "../models/product";
import {FoodService} from "../services/food.service";



@Component({
  selector: 'tr[app-product-edit]',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {

  @Input()
  item: Product | any;

  upitem: any = {};


  constructor(private foodService: FoodService) {
  }

  ngOnInit(): void {
  }

  setUpItem(){
    if((<HTMLInputElement>document.getElementById("active")).checked)
      this.item.active=1;
    else
      this.item.active=0;

    this.item.title=(<HTMLInputElement>document.getElementById("title")).value;
    this.item.servingWeight=(<HTMLInputElement>document.getElementById("servingWeight")).value;
    this.item.fats=(<HTMLInputElement>document.getElementById("fats")).value;
    this.item.protein=(<HTMLInputElement>document.getElementById("protein")).value;
    this.item.calories=(<HTMLInputElement>document.getElementById("calories")).value;
    this.item.carbohydrates=(<HTMLInputElement>document.getElementById("carbohydrates")).value;
    this.item.price=(<HTMLInputElement>document.getElementById("price")).value;

  }

  updateItem() {
    this.setUpItem();
    this.foodService.updateFoodProduct(this.item,this.item.id).subscribe((data)=>{
      console.log(data);
    },error => {
      console.log(error);
    });
  }

  deleteItem() {
    console.log(this.item.id);
    this.foodService.deleteFoodProduct(this.item.id).subscribe((data) => {
        window.location.reload();
        console.log(data);
      },
      error => {
        console.log(error);
      });
  }


}
