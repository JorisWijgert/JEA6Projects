import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NiksComponent } from './niks.component';

describe('NiksComponent', () => {
  let component: NiksComponent;
  let fixture: ComponentFixture<NiksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NiksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NiksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
