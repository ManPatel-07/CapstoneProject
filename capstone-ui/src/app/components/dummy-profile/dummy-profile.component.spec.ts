import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DummyProfileComponent } from './dummy-profile.component';

describe('DummyProfileComponent', () => {
  let component: DummyProfileComponent;
  let fixture: ComponentFixture<DummyProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DummyProfileComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DummyProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
