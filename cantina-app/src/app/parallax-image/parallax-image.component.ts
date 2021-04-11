import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-parallax-image',
  templateUrl: './parallax-image.component.html',
  styleUrls: ['./parallax-image.component.css']
})
export class ParallaxImageComponent {
  @Input()
  img: String;

  constructor() {
    this.img="/assets/img/contact_top.jpg"
  }


}
