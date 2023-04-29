import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaxAgeFormComponent } from './max-age-form.component';

describe('MaxAgeFormComponent', () => {
  let component: MaxAgeFormComponent;
  let fixture: ComponentFixture<MaxAgeFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MaxAgeFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MaxAgeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
