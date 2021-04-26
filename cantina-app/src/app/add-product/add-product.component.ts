import {Component, OnInit} from '@angular/core';
import {Product} from "../models/product";

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  product: Product;
  title: any;

  constructor() {

  }

  ngOnInit(): void {
  }

  add(value: any) {
   // this.product=value;
    console.log(value.protein, value.description, value.title);
  }
}
