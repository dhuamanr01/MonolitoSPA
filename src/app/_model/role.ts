import { Usuario } from "./usuario";

export class Role{

    public id: number;
    public nombre: string;
    public createdAt: string;
    public updatedAt: string;
    public auth: string;
    public usuarios: Usuario[];

}