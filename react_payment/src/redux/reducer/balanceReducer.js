import { TOPUP_BALANCE } from "../action/balanceAction";

const initialState = {
    balance: 0,
};

const balanceReducer = (state = initialState, action) => {
    switch (action.type) {
        case TOPUP_BALANCE :
            return { balance: action.topup};
    default:
        return state;
} };
export default balanceReducer;