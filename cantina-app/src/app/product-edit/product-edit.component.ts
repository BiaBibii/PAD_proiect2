import {Component, Input, OnInit, Output} from '@angular/core';
import {Product} from "../models/product";
import {FoodService} from "../services/food.service";
import {ToastrService} from "ngx-toastr";



@Component({
  selector: 'tr[app-product-edit]',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {

  @Input()
  item: Product | any;
  title: any;
  servingWeight: any;
  fats: any;
  protein: any;
  calories: any;
  carbohydrates: any;
  price: any;



  constructor(private toastr: ToastrService,private foodService: FoodService) {
  }

  ngOnInit(): void {
  }

  setUpItem(){
    console.log(this.item.active);

    this.item.active=(<HTMLInputElement>document.getElementById("active"+this.item.id)).checked;
    console.log(this.item.active);


    this.item.title=(<HTMLInputElement>document.getElementById("title"+this.item.id)).value;
    this.item.servingWeight=(<HTMLInputElement>document.getElementById("servingWeight"+this.item.id)).value;
    this.item.fats=(<HTMLInputElement>document.getElementById("fats"+this.item.id)).value;
    this.item.protein=(<HTMLInputElement>document.getElementById("protein"+this.item.id)).value;
    this.item.calories=(<HTMLInputElement>document.getElementById("calories"+this.item.id)).value;
    this.item.carbohydrates=(<HTMLInputElement>document.getElementById("carbohydrates"+this.item.id)).value;
    this.item.price=(<HTMLInputElement>document.getElementById("price"+this.item.id)).value;
    this.item.qty=(<HTMLInputElement>document.getElementById("qty"+this.item.id)).value;
  }

  updateItem() {
    this.setUpItem();
    console.log(this.item);
    this.foodService.updateFoodProduct(this.item,this.item.id).subscribe((data)=>{
      console.log(data);
      this.toastr.success("The product was updated");
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
