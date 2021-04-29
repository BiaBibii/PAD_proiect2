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



  constructor(private foodService: FoodService) {
  }

  ngOnInit(): void {
  }

  updateItem() {

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
