//
//  AboutScreen.swift
//  iosApp
//
//  Created by MARINHO Vitor on 02/09/2025.
//

import SwiftUI

struct AboutScreen: View {
    var body: some View {
        NavigationStack {
            AboutListScreen()
                .navigationBarTitle("About Device")
        }
    }
}

#Preview {
    AboutScreen()
}
