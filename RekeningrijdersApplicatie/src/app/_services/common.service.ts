import { Injectable } from '@angular/core';
import { User } from '../_model/User';

@Injectable()
export class CommonService {

    private username = '';
    private password = '';
    private rekeningrijderId: number;

    constructor() { }

    public getUsername(): string {
        return this.username;
    }

    public setUsername(username: string) {
        this.username = username;
    }

    public getPassword(): string {
        return this.password;
    }

    public setPassword(password: string) {
        this.password = password;
    }

    public getRekeningrijderId(): number {
        return this.rekeningrijderId;
    }

    public setRekeningrijderId(rekeningrijderId: number) {
        this.rekeningrijderId = rekeningrijderId;
    }
}
