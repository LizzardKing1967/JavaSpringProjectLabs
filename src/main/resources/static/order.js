    function updateTotalPrice() {
            // Получаем значение цены материала из элемента <p>
            const priceText = document.getElementById('materialPrice').textContent;
            const price = parseFloat(priceText.replace('Цена: ', '').replace(' ₽', '').trim());
            // Получаем значение количества
            const quantity = parseInt(document.getElementById('quantity').value) || 0;
            // Вычисляем итоговую стоимость
            const totalPrice = price * quantity;
            // Обновляем отображение итоговой стоимости
            document.getElementById('totalPrice').textContent = totalPrice.toFixed(2);
        }
