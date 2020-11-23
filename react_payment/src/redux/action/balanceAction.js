// testAction.js
export const TOPUP_BALANCE = "TOPUP_BALANCE";

export const topUp = (money) => {
    return { type: TOPUP_BALANCE, topup: money };
};