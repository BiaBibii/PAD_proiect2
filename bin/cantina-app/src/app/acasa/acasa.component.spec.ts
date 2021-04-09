import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcasaComponent } from './acasa.component';

describe('AcasaComponent', () => {
  let component: AcasaComponent;
  let fixture: ComponentFixture<AcasaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AcasaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AcasaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
