import { Routes } from '@angular/router';
import { ListComponent } from './admin/task/list/list.component';
import { EditComponent } from './admin/task/edit/edit.component';
import { AddComponent } from './admin/task/add/add.component';

export const routes: Routes = [
    {path: '', redirectTo: 'list', pathMatch: 'full' },
    {path:'list', component: ListComponent},
    {path:'edit/:id', component: EditComponent},
    {path:'add', component: AddComponent}

];
