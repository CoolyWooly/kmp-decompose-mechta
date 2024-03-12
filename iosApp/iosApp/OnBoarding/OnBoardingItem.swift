import Foundation
import SwiftUI
import Shared

struct OnBoardingModel: Identifiable {
    var id = UUID()
    var title: String
    var text: String
    var image: String
}


let itemsData: [OnBoardingModel] = [
    OnBoardingModel(
        title: Strings().get(id: MR.strings().onboard_text_1, args: []),
        text: Strings().get(id: MR.strings().onboard_text_1, args: []),
        image: "blueberry"
    ),
    OnBoardingModel(
        title: Strings().get(id: MR.strings().onboard_title_2, args: []),
        text: Strings().get(id: MR.strings().onboard_text_2, args: []),
        image: "strawberry"
    ),
    OnBoardingModel(
        title: Strings().get(id: MR.strings().onboard_title_3, args: ["qwer"]),
        text: Strings().get(id: MR.strings().onboard_text_3, args: []),
        image: "strawberry"
    )
]
