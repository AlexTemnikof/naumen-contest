import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAgeByNameComponent } from './get-age-by-name.component';

describe('GetAgeByNameComponent', () => {
  let component: GetAgeByNameComponent;
  let fixture: ComponentFixture<GetAgeByNameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetAgeByNameComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetAgeByNameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
