const submit = (action, dataId) => {
    const input = document.querySelector(`input[action-id='${dataId}']`);
    input.value = action;
    const form = document.querySelector(`form[data-id='${dataId}']`);
    form.submit();
};

const submitSearch = () => {
    const searchForm = document.querySelector("#search--form");
    searchForm.submit();
};
