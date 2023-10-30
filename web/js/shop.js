const decreaseQuantity = (dataId) => {
    const input = document.querySelector(`input[data-id='${dataId}']`);
    let value = parseInt(input.value);
    if (value > 0)
        value--;
    input.value = value;
};
const increaseQuantity = (dataId) => {
    const input = document.querySelector(`input[data-id='${dataId}']`);
    let value = parseInt(input.value);
    const maxvalue = input.getAttribute("max");
    if (value < maxvalue) {
        value++;
    }
    input.value = value;
};

const submit = () => {
    const searchForm = document.querySelector("#search--form");
    searchForm.submit();
};


