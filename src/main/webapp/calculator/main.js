// CALCULATOR
const calculator = document.querySelector('.jsCalculator');
const display = calculator.querySelector('.cDisplay');
const keys = calculator.querySelector('.cKeys');
const calculate = (firstValue, operator, secondValue) => {
    let result = '';
    let n1 = parseFloat(firstValue);
    let n2 = parseFloat(secondValue);

    switch (operator) {
        case 'add': result = n1 + n2; break;
        case 'subtract': result = n1 - n2; break;
        case 'multiply': result = n1 * n2; break;
        case 'divide': result = n1 / n2; break;
    }

    return result;
}

keys.addEventListener('click', e => {
    if (e.target.matches('button')) {

        const key = e.target;
        const action = key.dataset.action;
        const keyContent = key.textContent;
        const displayContent = display.textContent;

        if (action) actionOperator(action, displayContent);
        else noActionOperator(keyContent, displayContent);

        /*
        if (action) runAction(action, displayContent);
        if (!action) noAction(keyContent, displayContent);
        */

    }
})

function actionOperator(action, displayContent) {

    switch (action) {

        case 'add':
        case 'subtract':
        case 'multiply':
        case 'divide':
            calculator.dataset.operator = action;
            calculator.dataset.firstValue = displayContent;
            calculator.dataset.previousKeyType = 'operator';
            break;

        case 'decimal':
            if (!displayContent.includes('.') && displayContent != '0') {
                display.textContent = displayContent + '.';
                calculator.dataset.previousKeyType = 'decimal';
            }
            break;
        
        case 'calculate':
            const firstValue = calculator.dataset.firstValue;
            const operator = calculator.dataset.operator;
            const secondValue = displayContent;

            display.textContent = calculate(firstValue, operator, secondValue);
            calculator.dataset.operator = '';
            calculator.dataset.firstValue = display.textContent;
            calculator.dataset.previousKeyType = 'calculate';
            break;

        case 'clear':
            display.textContent = '0';
            calculator.dataset.firstValue = '';
            calculator.dataset.operator = '';
            calculator.dataset.previousKeyType = 'clear';
            
        default:
            break;

    }


}

function noActionOperator(keyContent, displayContent){

    if (displayContent === '0' || calculator.dataset.previousKeyType === 'operator')
    display.textContent = keyContent;
    else display.textContent = displayContent + keyContent;

    calculator.dataset.previousKeyType = 'number';

}