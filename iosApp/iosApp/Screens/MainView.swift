//
//  MainView.swift
//  iosApp
//
//  Created by MARINHO Vitor on 03/09/2025.
//

import SwiftUI

struct MainView: View {
    @State private var shouldOpenAbout = false
    
    var body: some View {
        NavigationStack {
            ArticlesScreen(viewModel: .init())
                .toolbar {
                    ToolbarItem {
                        Button {
                            shouldOpenAbout = true
                        } label: {
                            Label("About", systemImage: "info.circle")
                                .labelStyle(.titleAndIcon)
                        }
                        .popover(isPresented: $shouldOpenAbout) {
                            AboutScreen()
                        }
                    }
                }
        }
    }
}

#Preview {
    MainView()
}
