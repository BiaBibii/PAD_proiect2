import {Component, OnInit} from '@angular/core';
import {FoodService} from "../services/food.service";
import {Product} from "../models/product";
import {ActivatedRoute, Router} from "@angular/router";
import {CartService} from "../services/cart.service";
import {HttpClient} from "@angular/common/http";
@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  item: Product | any;
  id: number | any;
  selectedFile: File|any;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string|any;
  imageName: any;
  constructor(private httpClient: HttpClient, private cartService: CartService, private foodService: FoodService, private router: Router, private route: ActivatedRoute) {
  }
  //Gets called when the user clicks on retieve image button to get the image from back end
  getImage(id: any) {
    //Make a call to Sprinf Boot to get the Image Bytes.
	id=id+1;
    this.httpClient.get('http://localhost:8080/api/foods/get/' + id)
      .subscribe(
    res => {
	console.log(res);
      this.retrieveResonse = res;
      this.base64Data = this.retrieveResonse.pic;
      this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
    }, err =>{console.log(err);}
  );
  }
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = +params['id']; // (+) converts string 'id' to a number
      console.log(this.id);
    });
    this.foodService.getFoodProductById(this.id).subscribe(data => {
      this.item = data;
      this.getImage(this.id);
    });
  }
  addItemToCart() {
    this.cartService.addProductToCart(this.id).subscribe((data) => {
        console.log(data);
      },
      error => {
        console.log(error);
      });
  }
}
