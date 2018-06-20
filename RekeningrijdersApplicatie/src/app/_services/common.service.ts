import { Injectable } from '@angular/core';
import { User } from '../_model/User';

@Injectable()
export class CommonService {

    public username = '';
    public password = '';
    public rekeningrijderId: number;

    constructor() { }
}
