const type = "SAVE";

const init = {
  id: "100",
};

export const Action = (id) => {
  return { type: type, id };
};

export const Reducer = (state = init, action) => {
  switch (action.type) {
    case type:
      return { id: action.id };
    default:
      return state;
  }
};
