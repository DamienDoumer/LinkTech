const {BundleAnalyzerPlugin} = require("webpack-bundle-analyzer");
const WebpackBar = require("webpackbar");
const CracoAntDesignPlugin = require("craco-antd");
const path = require("path");

process.env.BROWSER = "none";
process.env.SKIP_PREFLIGHT_CHECK = true;

module.exports = {
    webpack: {
        plugins: [
            new WebpackBar({profile: true}),
            ...(process.env.NODE_ENV === "development"
                ? [new BundleAnalyzerPlugin({openAnalyzer: false})]
                : [])
        ]
    },
    plugins: [
        // {plugin: require("craco-preact")},
        {
            plugin: CracoAntDesignPlugin,
            options: {
                customizeThemeLessPath: path.join(
                    __dirname,
                    "src/antd.customize.less"
                )
            }
        }
    ]
};
