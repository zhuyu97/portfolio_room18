export interface AllHoldAssets {
    msg : string,
    code : number,
    data : Array<{
        holdAssetsId: number,
        userId: number,
        productionId: number,
        productionTypeName: string,
        productName:string
        productionAmount: number,
        holdingCost: number,
        income: number,
        incomeRate : number,
    }>
}
