import {Component, OnInit} from '@angular/core';
import {FoodService} from "../services/food.service";
import {Product} from "../models/product";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  item: Product| any;
  id: number| any;

  constructor(private foodService: FoodService, private router: Router,private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = +params['id']; // (+) converts string 'id' to a number
      console.log(this.id);
    });
    this.foodService.getFoodProductById(this.id).subscribe(data=>{
      this.item=data;
    });

  }

}
