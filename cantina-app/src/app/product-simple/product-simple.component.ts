import {Component, Input, OnInit} from '@angular/core';
import {Product} from "../models/product";

@Component({
  selector: 'app-product-simple',
  templateUrl: './product-simple.component.html',
  styleUrls: ['./product-simple.component.css']
})
export class ProductSimpleComponent implements OnInit {

  @Input()
  item: Product| any;

  constructor() { }

  ngOnInit(): void {
  }

}
