import {Component, OnInit} from '@angular/core';
import {Product} from "../models/product";
import {FoodService} from "../services/food.service";

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  product: Product | undefined;
  title: any;
  form: any = {};
  selectedFile: File| any;

  constructor(private foodService: FoodService) {

  }

  ngOnInit(): void {
  }

  onFileChanged($event: Event) {
    // @ts-ignore
    this.selectedFile = event.target.files[0];
  }



  addProduct() {
    const uploadData = new FormData();
    uploadData.append('foodProductImage', this.selectedFile);
    this.form.foodProductImage=null;
    console.log(this.form.foodProductImage);
    this.foodService.addFoodProduct(this.form).subscribe((data) => {
        console.log(data);
      },
      error => {
        console.log(error);
      });
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
