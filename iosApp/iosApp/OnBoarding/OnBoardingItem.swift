import Foundation
import SwiftUI

struct OnBoardingItem: Identifiable {
    var id = UUID()
    var title: String
    var text: String
    var image: String
}


let itemsData: [OnBoardingItem] = [
    OnBoardingItem(
        title: "Гарантия низкой цены",
        text: "Самые низкие цены на более, чем 10 000 товаров! Если найдете дешевле, мы снизим цену!",
        image: "blueberry"
    ),
    OnBoardingItem(
        title: "Вернём CashBack бонусами",
        text: "Дарим бонусы за покупки! Бонусами можно оплатить всю стоимость товара",
        image: "strawberry"
    ),
    OnBoardingItem(
        title: "Ваш город Алматы?",
        text: "Укажите ваш город, чтобы увидеть актуальные товары с быстрой доставкой",
        image: "strawberry"
    )
]
